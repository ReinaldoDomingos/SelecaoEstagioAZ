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
	 (13, 'Barbearia do João', '20722342576523', 'Rua dos geranios','Coxim', '65', null, 'Centro', '79400000', '67979187766', 'reijunior100@gmail.com', 'https://github.com/ReinaldoDomingos/', 'joao99', 'jsoajoda')