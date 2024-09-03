package com.pbaris.jmix.mlf.liquibase;

import io.jmix.appsettings.AppSettings;
import liquibase.database.Database;
import liquibase.exception.CustomPreconditionErrorException;
import liquibase.exception.CustomPreconditionFailedException;
import liquibase.precondition.CustomPrecondition;

/**
 * @author Panos Bariamis (pbaris)
 */
public class AppSettingsPrecondition implements CustomPrecondition {

    @Override
    public void check(final Database database) throws CustomPreconditionFailedException, CustomPreconditionErrorException {
        try {
            Class.forName(AppSettings.class.getCanonicalName());

        } catch (ClassNotFoundException e) {
            throw new CustomPreconditionFailedException("Class " + AppSettings.class.getSimpleName() + " does not exist in the classpath.", e);
        }
    }
}
