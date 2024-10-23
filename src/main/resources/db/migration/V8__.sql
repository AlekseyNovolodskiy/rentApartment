ALTER TABLE user_info
    ADD verivication VARCHAR(255);

ALTER TABLE address_info
    ADD CONSTRAINT uc_address_info_apartment UNIQUE (apartment_id);

ALTER TABLE user_info
    DROP COLUMN verification;

ALTER TABLE address_info
    ALTER COLUMN building_number TYPE VARCHAR(255) USING (building_number::VARCHAR(255));

ALTER TABLE address_info
    ALTER COLUMN city TYPE VARCHAR(255) USING (city::VARCHAR(255));

ALTER TABLE rate_apartment
    ALTER COLUMN comments TYPE VARCHAR(2000) USING (comments::VARCHAR(2000));

ALTER TABLE apartment_info
    ALTER COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN fruits TYPE VARCHAR(255) USING (fruits::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN fruits_amount SET NOT NULL;

ALTER TABLE delive_info
    ALTER COLUMN fruits_price SET NOT NULL;

ALTER TABLE delive_info
    ALTER COLUMN juices TYPE VARCHAR(255) USING (juices::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN juices_amount SET NOT NULL;

ALTER TABLE delive_info
    ALTER COLUMN juices_price SET NOT NULL;

ALTER TABLE integration_info
    ALTER COLUMN key_info TYPE VARCHAR(255) USING (key_info::VARCHAR(255));

ALTER TABLE user_info
    ALTER COLUMN login TYPE VARCHAR(255) USING (login::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN meat TYPE VARCHAR(255) USING (meat::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN meat_amount SET NOT NULL;

ALTER TABLE delive_info
    ALTER COLUMN meat_price SET NOT NULL;

ALTER TABLE integration_info
    ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

ALTER TABLE user_info
    ALTER COLUMN nick_name TYPE VARCHAR(255) USING (nick_name::VARCHAR(255));

ALTER TABLE user_info
    ALTER COLUMN password TYPE VARCHAR(255) USING (password::VARCHAR(255));

ALTER TABLE address_info
    ALTER COLUMN street TYPE VARCHAR(255) USING (street::VARCHAR(255));

ALTER TABLE user_info
    ALTER COLUMN token TYPE VARCHAR(255) USING (token::VARCHAR(255));

ALTER TABLE integration_info
    ALTER COLUMN url TYPE VARCHAR(255) USING (url::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN vegetables TYPE VARCHAR(255) USING (vegetables::VARCHAR(255));

ALTER TABLE delive_info
    ALTER COLUMN vegetables_amount SET NOT NULL;

ALTER TABLE delive_info
    ALTER COLUMN vegetables_price SET NOT NULL;