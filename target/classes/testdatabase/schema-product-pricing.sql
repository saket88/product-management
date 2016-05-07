drop table T_PRODUCT_PRICE if exists;

create table T_PRODUCT_PRICE (ID bigint identity primary key , PRODUCT_CODE varchar(9),
                        PRICE decimal(8,2));
                        

