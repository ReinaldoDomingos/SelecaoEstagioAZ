-- Table: public.tb_comprador

-- DROP TABLE public.tb_comprador;

CREATE TABLE public.tb_comprador
(
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
INSERT INTO public.tb_comprador(empresa, leilao) VALUES
    (1, 2),(2,1),(2,10),(3,2),(5,4),(4,7),
	(9,5),(12,12),(13,1),(5,5),(8,9),(10,9),(3,4),(9,11)