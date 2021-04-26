/* Copyright (c) 2016 LibJ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.libj.sql.exception;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLSyntaxErrorException;

import org.libj.lang.Throwables;

/**
 * A catalog of strong exception types that can be dereferenced by the
 * {@link SQLException#getSQLState()} via {@link #toStrongType(SQLException)}.
 */
public final class SQLExceptions {
  /**
   * Returns the strong exception type for the specified {@link SQLException},
   * or {@code null} if one is not registered. The specified exception's
   * {@link SQLException#getSQLState()} method is used to dereference the
   * appropriate strong exception type.
   *
   * @param exception The {@link SQLException}.
   * @return The strong exception type for the specified {@link SQLException},
   *         or {@code null} if one is not registered.
   */
  public static SQLException toStrongType(final SQLException exception) {
    final SQLException e;
    final String sqlState = exception.getSQLState();
    if (sqlState == null || sqlState.length() < 2) {
      if (exception.getMessage().contains("A PRIMARY KEY constraint failed")) // SQLite
        e = new SQLIntegrityConstraintViolationException(exception.getMessage(), sqlState, exception.getErrorCode());
      else
        return exception;
    }
    else {
      final String _class = sqlState.substring(0, 2);
      if ("02".equals(_class)) {
        if (exception instanceof SQLNoDataException)
          return exception;

        e = new SQLNoDataException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("07".equals(_class)) {
        if (exception instanceof SQLDynamicErrorException)
          return exception;

        e = new SQLDynamicErrorException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("08".equals(_class) || "XJ".equals(_class)) { // XJ is Connectivity Error for Derby
        if (exception instanceof SQLConnectionException)
          return exception;

        e = new SQLConnectionException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("0A".equals(_class)) {
        if (exception instanceof SQLFeatureNotSupportedException)
          return exception;

        e = new SQLFeatureNotSupportedException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("21".equals(_class)) {
        if (exception instanceof SQLCardinalityException)
          return exception;

        e = new SQLCardinalityException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("22".equals(_class)) {
        if (exception instanceof SQLDataException)
          return exception;

        e = new SQLDataException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("23".equals(_class)) {
        if (exception instanceof SQLIntegrityConstraintViolationException)
          return exception;

        e = new SQLIntegrityConstraintViolationException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("24".equals(_class)) {
        if (exception instanceof SQLInvalidCursorStateException)
          return exception;

        e = new SQLInvalidCursorStateException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("25".equals(_class)) {
        if (exception instanceof SQLInvalidTransactionStateException)
          return exception;

        e = new SQLInvalidTransactionStateException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("26".equals(_class)) {
        if (exception instanceof SQLInvalidStatementNameException)
          return exception;

        e = new SQLInvalidStatementNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("28".equals(_class)) {
        if (exception instanceof SQLInvalidAuthorizationSpecException)
          return exception;

        e = new SQLInvalidAuthorizationSpecException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("2B".equals(_class)) {
        if (exception instanceof SQLDependentPrivilegeDescriptorsException)
          return exception;

        e = new SQLDependentPrivilegeDescriptorsException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("2C".equals(_class)) {
        if (exception instanceof SQLInvalidCharacterSetNameException)
          return exception;

        e = new SQLInvalidCharacterSetNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("2D".equals(_class)) {
        if (exception instanceof SQLInvalidTransactionTerminationException)
          return exception;

        e = new SQLInvalidTransactionTerminationException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("2E".equals(_class)) {
        if (exception instanceof SQLInvalidConnectionNameException)
          return exception;

        e = new SQLInvalidConnectionNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("33".equals(_class)) {
        if (exception instanceof SQLInvalidDescriptorNameException)
          return exception;

        e = new SQLInvalidDescriptorNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("34".equals(_class)) {
        if (exception instanceof SQLInvalidCursorNameException)
          return exception;

        e = new SQLInvalidCursorNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("35".equals(_class)) {
        if (exception instanceof SQLInvalidConditionNumberException)
          return exception;

        e = new SQLInvalidConditionNumberException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("3C".equals(_class)) {
        if (exception instanceof SQLAmbiguousCursorNameException)
          return exception;

        e = new SQLAmbiguousCursorNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("3D".equals(_class)) {
        if (exception instanceof SQLInvalidCatalogNameException)
          return exception;

        e = new SQLInvalidCatalogNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("3F".equals(_class)) {
        if (exception instanceof SQLInvalidSchemaNameException)
          return exception;

        e = new SQLInvalidSchemaNameException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("40".equals(_class)) {
        if (exception instanceof SQLTransactionException)
          return exception;

        e = new SQLTransactionException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("42".equals(_class)) {
        if (exception instanceof SQLSyntaxErrorException)
          return exception;

        e = new SQLSyntaxErrorException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("65".equals(_class)) { // Oracle: https://www.techonthenet.com/oracle/errors/ora06502.php
        if (exception instanceof SQLValueException)
          return exception;

        e = new SQLValueException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else if ("XX".equals(_class)) {
        if (exception instanceof SQLInternalErrorException)
          return exception;

        e = new SQLInternalErrorException(exception.getMessage(), sqlState, exception.getErrorCode());
      }
      else {
        final UnsupportedOperationException uoe = new UnsupportedOperationException("Unsupported class: " + _class + ", SQL State: " + exception.getSQLState() + ", error code: " + exception.getErrorCode());
        uoe.addSuppressed(exception);
        throw uoe;
      }
    }

    if (exception.getClass() == SQLException.class)
      return Throwables.copy(exception, e);

    e.initCause(exception);
    return e;
  }

  // Spec: http://www.contrib.andrew.cmu.edu/~shadow/sql/sql1992.txt
  // Derby: http://web.mit.edu/course/13/13.715/jdk1.6.0_18/db/docs/html/ref/ref-single.html https://db.apache.org/derby/docs/10.4/ref/rrefexcept71493.html
  // Oracle: https://docs.oracle.com/javadb/10.8.3.0/ref/rrefexcept71493.html
  // PostgreSQL: https://www.postgresql.org/docs/9.2/errcodes-appendix.html
  // MySQL: ???
  private SQLExceptions() {
  }
}