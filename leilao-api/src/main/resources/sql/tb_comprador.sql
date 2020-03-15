-- Table: public.tb_comprador

-- DROP TABLE public.tb_comprador;

CREATE TABLE public.tb_comprador
(
    id integer NOT NULL,
    empresa integer NOT NULL,
    leilao integer NOT NULL,
    CONSTRAINT comprador_pk PRIMARY KEY (empresa, leilao),
    CONSTRAINT empresa_comp_fk FOREIGN KEY (empresa)
        REFERENCES public.tb_empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT leilao_comp_fk FOREIGN KEY (leilao)
        REFERENCES public.tb_leilao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tb_comprador
    OWNER to postgres;



-- INSERTS NA TABELA COMPRADOR
INSERT INTO public.tb_comprador(id,empresa, leilao) VALUES
    (1,1, 2),(2,2,1),(3,2,10),(4,3,2),(5,5,4),(6,4,7),(7,9,5),
	(8,12,12),(9,13,1),(10,5,5),(11,8,9),(12,10,9),(13,3,4),(14,9,11);