insert into exchange_value(id,currency_from,currency_to,conversion_multiple)
values(1001,'USD','INR',70);
insert into exchange_value(id,currency_from,currency_to,conversion_multiple)
values(1002,'EUR','INR',112);
insert into exchange_value(id,currency_from,currency_to,conversion_multiple)
values(1003,'AUD','INR',25);


UPDATE exchange_value
SET Currency_from='USD',currency_to='INR',conversion_multiple=500
WHERE id=1001;



