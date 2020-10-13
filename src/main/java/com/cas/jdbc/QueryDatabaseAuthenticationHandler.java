/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.cas.jdbc;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that if provided a query that returns a password (parameter of query
 * must be username) will compare that password to a translated version of the
 * password provided by the user. If they match, then authentication succeeds.
 * Default password translator is plaintext translator.
 *
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @author Marvin S. Addison
 * @since 3.0
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @NotNull
    private String sql;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {

        final String username = credential.getUsername();
        try {
            Member member = findMember(username);
            if (member == null) {
                throw new AccountNotFoundException(username + " not found");
            }
            //final String encPass = getPasswordEncoder().encode(getPasswordEncoder().encode(credential.getPassword())+member.getSalt());
            //final String encPass = getPasswordEncoder().encode(credential.getPassword());
            //if (!member.getPassword().equals(encPass)) {
            //	throw new FailedLoginException("Password does not match value on record.");
            //}
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }


    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }

    private Member findMember(String username) {
        boolean ismobile = false;

        String propertyName = ismobile ? "mobile" : (KsdUtils.isEmail(username) ? "email" : "username");
        String sql = "select * from sys_user where " + propertyName + " = ?";
        Member member = null;
        try {
            member = (Member) getJdbcTemplate().queryForObject(sql, new RowMapper<Object>() {

                @Override
                public Object mapRow(ResultSet resultSet, int arg1) {
                    Member member = new Member();
                    try {
						/*
						member.setMemberId(resultSet.getInt("member_id"));
						member.setEmail(resultSet.getString("member_email"));
						member.setUsername(resultSet.getString("member_name"));
						member.setMobile(resultSet.getString("member_mobile"));
						member.setPassword(resultSet.getString("member_passwd"));
						*/
                        member.setMemberId(resultSet.getInt("id"));
                        member.setEmail(resultSet.getString("email"));
                        member.setUsername(resultSet.getString("nick_name"));
                        member.setMobile(resultSet.getString("phone"));
                        member.setPassword(resultSet.getString("password"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        member = null;
                    }
                    return member;
                }
            }, username);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return member;
    }

    private Member findShopncMember(String username) {
        boolean ismobile = false;

        String propertyName = ismobile ? "mobile" : (KsdUtils.isEmail(username) ? "member_email" : "member_name");
        String sql = "select * from shopnc_member where " + propertyName + " = ?";

        Member member = null;
        try {
            member = (Member) getJdbcTemplate().queryForObject(sql, new RowMapper<Object>() {

                @Override
                public Object mapRow(ResultSet resultSet, int arg1) {
                    Member member = new Member();
                    try {

                        member.setMemberId(resultSet.getInt("member_id"));
                        member.setEmail(resultSet.getString("member_email"));
                        member.setUsername(resultSet.getString("member_name"));
                        member.setMobile(resultSet.getString("member_mobile"));
                        member.setPassword(resultSet.getString("member_passwd"));


                    } catch (SQLException e) {
                        e.printStackTrace();
                        member = null;
                    }
                    return member;
                }
            }, username);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return member;
    }
}
