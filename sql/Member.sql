insert
    into
        member
        (address1, address2, address3, address4, area, country, zip_code, email_address, lockyn, member_platform, member_status, first_name, last_name, password, password_failure_count, member_sn)
    values
        ('123123', '231231', '444333', '123123', null, null, '123-1234', 'eric.lee@uzen.net', 'Y', 'BackOffice', 'Normal', 'LEE', 'Sanguk', '$2a$10$I2NX7u87WDK5A42lLaWac.V.xS8gpiLXykBtCFam7Agupuklt/Lim', '0', (SELECT NEXT VALUE FOR MEMBER_SEQ));
