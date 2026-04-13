select WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, IFNULL(FREEZER_YN, "N")
from food_warehouse
where address like '%경기%';