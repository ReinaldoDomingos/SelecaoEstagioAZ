-- Table: public.tb_leilao

-- DROP TABLE public.tb_leilao;

CREATE TABLE public.tb_leilao
(
    id integer NOT NULL,
    codigo integer,
    descricao character varying(60) COLLATE pg_catalog."default" NOT NULL,
    vendedor integer NOT NULL,
    inicio_previsto timestamp with time zone NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT leilao_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tb_leilao
    OWNER to postgres;

-- Trigger: set_timestamp

-- DROP TRIGGER set_timestamp ON public.tb_leilao;

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON public.tb_leilao
    FOR EACH ROW
    EXECUTE PROCEDURE public.trigger_set_timestamp();




-- INSERTS NA TABELA LEILAO
INSERT INTO public.tb_leilao(id, codigo, descricao, vendedor, inicio_previsto) VALUES
(1, 10,'leilao 1', 1, '2020-03-01'),
(2, 10,'leilao 2', 1, '2019-03-11'),
(3, 10,'leilao 3', 1, '2015-09-13'),
(4, 10,'leilao 4', 1, '2020-01-20'),
(5, 10,'leilao 5', 1, '2020-03-15'),
(6, 10,'leilao 6', 1, '2021-02-09'),
(7, 10,'leilao 7', 1, '2019-03-08'),
(8, 10,'leilao 8', 1, '2022-04-05'),
(9, 10,'leilao 9', 1, '2018-03-21'),
(10, 10,'leilao 10', 1, '2020-07-29'),
(11, 10,'leilao 11', 1, '2011-02-28'),
(12, 10,'leilao 12', 1, '2001-03-02');