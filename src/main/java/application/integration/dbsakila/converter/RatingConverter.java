package application.integration.dbsakila.converter;

import application.common.types.Rating;

import javax.persistence.AttributeConverter;

/**
 * Magic enum converter. It converts enum with dash e.g. PG-13 to enum without dash. So PG-13 will be PG13.
 *
 * Created by Adam_Skowron on 11.08.2016.
 */
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        if ( attribute == null ) {
            return null;
        }
        return attribute.getRatingName();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        if ( dbData == null ) {
            return null;
        }
        return Rating.valueOf(dbData.replace("-",""));
    }
}