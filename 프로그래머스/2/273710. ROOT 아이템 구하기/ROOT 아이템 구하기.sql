-- 코드를 작성해주세요
SELECT info.ITEM_ID as ITEM_ID, ITEM_NAME
FROM ITEM_INFO as info 
JOIN ITEM_TREE as tree
ON info.item_id = tree.item_id
WHERE parent_item_id is NULL;