-- 조회할 정보
SELECT A.product_id, B.product_name, sum(amount)*B.price total_sales
-- 총매출 계산을 위한 join
from food_order A join food_product B
on A.product_id = B.product_id
-- 조건1
where date_format(produce_date, '%Y-%m') = '2022-05'
group by product_name
-- 조건2
order by total_sales desc, product_id ;