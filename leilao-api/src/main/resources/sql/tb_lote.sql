-- Table: public.tb_lote

-- DROP TABLE public.tb_lote;

CREATE TABLE public.tb_lote
(
    id            integer                                             NOT NULL,
    numero_lote   integer,
    descricao     character varying(60) COLLATE pg_catalog."default"  NOT NULL,
    quantidade    numeric                                             NOT NULL,
    valor_inicial numeric,
    unidade       character varying(128) COLLATE pg_catalog."default" NOT NULL,
    leilao        integer                                             NOT NULL,
    created_at    timestamp with time zone                            NOT NULL DEFAULT now(),
    updated_at    timestamp with time zone                            NOT NULL DEFAULT now(),
    CONSTRAINT lote_pk PRIMARY KEY (id),
    CONSTRAINT leilao_lote_fk FOREIGN KEY (leilao)
        REFERENCES public.tb_leilao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.tb_lote
    OWNER to postgres;

-- Trigger: set_timestamp

-- DROP TRIGGER set_timestamp ON public.tb_lote;

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON public.tb_lote
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_timestamp();


-- INSERTS NA TABELA LOTE
INSERT INTO public.tb_lote(id, numero_lote, descricao, quantidade, valor_inicial, unidade, leilao)
VALUES (1, 1, 'lote 1', 3, null, 'undidade', 2),
       (2, 21, 'lote 21', 3, null, 'undidade 4', 2),
       (3, 32, 'lote 32', 5, 50000, 'undidade 1', 5),
       (4, 3, 'lote 3', 7, 20000, 'undidade 9', 4),
       (5, 4, 'lote 4', 11, 15000, 'undidade 1', 6),
       (6, 5, 'lote 5', 21, null, 'undidade 2', 1),
       (7, 6, 'lote 6', 18, 17000, 'undidade 8', 11),
       (8, 7, 'lote 7', 9, 25000, 'undidade 6', 2),
       (9, 12, 'lote 12', 10, null, 'undidade 6', 7),
       (10, 9, 'lote 9', 13, 23000, 'undidade 3', 2),
       (11, 10, 'lote 10', 23, 50000, 'undidade 7', 4),
       (12, 11, 'lote 11', 50, 11500, 'undidade 8', 8),
       (13, 22, 'lote 22', 32, 30000, 'undidade 4', 12),
       (14, 13, 'lote 13', 33, 7000, 'undidade 5', 2),
       (15, 14, 'lote 14', 90, null, 'undidade 6', 3);

CREATE SEQUENCE seq_lote START 16;