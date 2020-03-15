-- FUNÇÂO PARA ATUALIZAR ATRIBUTO UPDATEDAT AUTOMATICO;
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- FUNÇÂO PARA ATUALIZAR ATRIBUTO UPDATEDAT AUTOMATICO;


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
(2, 10,'leilao 2', 2, '2019-03-11'),
(3, 10,'leilao 3', 5, '2015-09-13'),
(4, 10,'leilao 4', 7, '2020-01-20'),
(5, 10,'leilao 5', 13, '2020-03-15'),
(6, 10,'leilao 6', 2, '2021-02-09'),
(7, 10,'leilao 7', 10, '2019-03-08'),
(8, 10,'leilao 8', 5, '2022-04-05'),
(9, 10,'leilao 9', 6, '2018-03-21'),
(10, 10,'leilao 10', 9, '2020-07-29'),
(11, 10,'leilao 11', 12, '2011-02-28'),
(12, 10,'leilao 12', 11, '2001-03-02');
CREATE SEQUENCE seq_leilao START 13;


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
VALUES (1, 1, 'lote 1', 3, 10000, 'undidade', 2),
       (2, 21, 'lote 21', 3, 40000, 'undidade 4', 10),
       (3, 32, 'lote 32', 5, 50000, 'undidade 1', 5),
       (4, 3, 'lote 3', 7, 20000, 'undidade 9', 4),
       (5, 4, 'lote 4', 11, 15000, 'undidade 1', 6),
       (6, 5, 'lote 5', 21, 30000, 'undidade 2', 1),
       (7, 6, 'lote 6', 18, 17000, 'undidade 8', 11),
       (8, 7, 'lote 7', 9, 25000, 'undidade 6', 9),
       (9, 12, 'lote 12', 10, 35000, 'undidade 6', 7),
       (10, 9, 'lote 9', 13, 23000, 'undidade 3', 2),
       (11, 10, 'lote 10', 23, 50000, 'undidade 7', 4),
       (12, 11, 'lote 11', 50, 11500, 'undidade 8', 8),
       (13, 22, 'lote 22', 32, 30000, 'undidade 4', 12),
       (14, 13, 'lote 13', 33, 7000, 'undidade 5', 2),
       (15, 14, 'lote 14', 90, 75000, 'undidade 6', 3);

CREATE SEQUENCE seq_lote START 16;



-- Table: public.tb_empresa

-- DROP TABLE public.tb_empresa;

CREATE TABLE public.tb_empresa
(
    id integer NOT NULL,
    razao_social character varying(64) COLLATE pg_catalog."default" NOT NULL,
    cnpj character varying(32) COLLATE pg_catalog."default" NOT NULL,
    logradouro character varying(64) COLLATE pg_catalog."default",
    municipio character varying(64) COLLATE pg_catalog."default",
    numero character varying(10) COLLATE pg_catalog."default",
    complemento character varying(64) COLLATE pg_catalog."default",
    bairro character varying(64) COLLATE pg_catalog."default",
    cep character varying(16) COLLATE pg_catalog."default",
    telefone character varying(32) COLLATE pg_catalog."default",
    email character varying(254) COLLATE pg_catalog."default" NOT NULL,
    site character varying(254) COLLATE pg_catalog."default",
    usuario character varying(20) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(128) COLLATE pg_catalog."default",
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT empresa_pk PRIMARY KEY (id),
    CONSTRAINT empresa_cnpj_uk UNIQUE (cnpj),
    CONSTRAINT empresa_usuario_uk UNIQUE (usuario)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tb_empresa
    OWNER to postgres;

-- Trigger: set_timestamp

-- DROP TRIGGER set_timestamp ON public.tb_empresa;

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON public.tb_empresa
    FOR EACH ROW
    EXECUTE PROCEDURE public.trigger_set_timestamp();


-- INSERTS NA TABELA EMPRESA
INSERT INTO public.tb_empresa(id, razao_social, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha)
	VALUES (1, 'E-comerce JPA', '09207212376523', 'Rua dos geanios','Coxim', '123', null, 'Senhor Divino', '79400000', '67999887766', 'reijunior100@gmail.com', 'https://github.com/ReinaldoDomingos/', 'reijunior100', '1234'),
	 (2, 'Comércio de Bikes', '08207212376723', 'Rua dos tulipas','Coxim', '8', 'Perto do Zeca', 'Santa Maria', '79400000', '67997885761', 'reijunior100@gmail.com', 'https://www.microsoft.com/pt-br', 'vito157', 'dkaooa'),
	 (3, 'Tem de Tudo - Vendas e Varejos', '19207212376523', 'Av Mato Grosso do Sul','Coxim', '7', 'Casa sem portão', 'Senhor Divino', '79400000', '67979884766', 'reijunior100@gmail.com', 'https://github.com/ReinaldoDomingos/', 'tulio666', 'kdoajdo'),
	 (4, 'E-comerce do Zeca', '10920721227652', 'Rua das Flores','Coxim', '8', null, 'Senhor Divino', '79402010', '6798877262', 'reijunior100@gmail.com', 'http://heroku.com/', 'nicolas77', 'jdjakl'),
	 (5, 'Supermercado União', '921722376523', null,'Pedro Gomes', '9', '10', 'Senhor Divino', '79400000', '67999887766', 'reijunior100@gmail.com', 'https://github.com/cleitonalmeida1/SelecaoEstagioAZ', 'marcos27', '89u92dj'),
	 (6, 'Deliery ifood', '10920872124763', 'Rua Nova Campo Grande','Coxim', '99', null, 'Silviolandia', '79420040', '67999887765', 'reijunior100@gmail.com', 'http://google.com/', 'rj45', '82802'),
	 (7, 'Uber - Transportes de pessoas', '09207112376523', 'Rua dos geanios','Coxim', '13', 'Proximo ao IFMS', 'Senhor Divino', '79400000', '67999887769', 'http://heroku.com/', 'https://github.com/ReinaldoDomingos/', 'rei1000', '72812'),
	 (8, 'Google Inc', '02022233725213', 'Rua dos geanios','Campo Grande','12', 'Apartamento B', 'Nova Coxim', '79400100', '6699532776', 'reijunior100@gmail.com', 'http://google.com/', 'junior11', '7890'),
	 (9, 'Microsoft Inc', '39247215275723', null,'Sonora', '31', null, 'Senhor Divino', '79400000', '67999887766', 'reijunior100@gmail.com', 'http://www.ms.gov.br/', 'renato110', '1@2@3@4'),
	 (10, 'Minério SA', '19207232361522', 'Rua das Carmelias','Coxim', null, 'Ao lado do Espetinho JP', 'Centro', '78500000', '659934887764', 'reijunior100@gmail.com', 'http://www.ms.gov.br/', 'pedro100', 'admin@1234'),
	 (11, 'Farmácia do Ailton', '09207712376523', 'Rua Independencia','Alcinópolis', '55', 'Perto da Maria', 'Senhor Divino', '79400100', '67999887761', 'reijunior100@gmail.com', 'http://www.dataprev.gov.br/', 'felipe90', 'admin1234'),
	 (12, 'Loja Tigre', '1092721276523', 'Rua Vera de Souza','Coxim', '63', 'Casa A', 'Piracema', '79200001', '67929887766', 'reijunior100@gmail.com', 'http://google.com/', 'jorge10', '12345'),
	 (13, 'Barbearia do João', '20722342576523', 'Rua dos geranios','Coxim', '65', null, 'Centro', '79400000', '67979187766', 'reijunior100@gmail.com', 'https://github.com/ReinaldoDomingos/', 'joao99', 'jsoajoda');

CREATE SEQUENCE seq_empresa START 14;




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
    (1,7, 2),(2,5,1),(3,8,10),(4,9,2),(5,5,4),(6,8,7),(7,12,5),
	(8,11,12),(9,12,1),(10,11,5),(11,8,9),(12,10,9),(13,3,4),(14,9,11);