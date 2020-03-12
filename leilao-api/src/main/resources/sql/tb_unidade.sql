-- Table: public.tb_unidade

-- DROP TABLE public.tb_unidade;

CREATE TABLE public.tb_unidade
(
    id integer NOT NULL,
    nome character varying(128) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT unidade_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tb_unidade
    OWNER to postgres;

-- Trigger: set_timestamp

-- DROP TRIGGER set_timestamp ON public.tb_unidade;

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON public.tb_unidade
    FOR EACH ROW
    EXECUTE PROCEDURE public.trigger_set_timestamp();








-- INSERTS NA TABELA UNIDADE
INSERT INTO public.tb_unidade(id, nome)	VALUES
(1, 'Unidade 1') ,(2, 'Unidade 2'), (3, 'Unidade 3')
, (4, 'Unidade 4'), (5, 'Unidade 5'), (6, 'Unidade 6')
, (7, 'Unidade 7'), (8, 'Unidade 8'), (9, 'Unidade 9')
, (10, 'Unidade 10'),(11, 'Unidade 11'), (12, 'Unidade 12')
, (13, 'Unidade 13'),(14, 'Unidade 14'),(15, 'Unidade 15');

CREATE SEQUENCE seq_unidade START 16;