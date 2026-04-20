SELECT c.id as ID, c.genotype as GENOTYPE, p.genotype as PARENT_GENOTYPE
FROM ECOLI_DATA as c JOIN ECOLI_DATA as p on c.parent_id = p.id
WHERE c.genotype & p.genotype = p.genotype
ORDER BY ID asc;
