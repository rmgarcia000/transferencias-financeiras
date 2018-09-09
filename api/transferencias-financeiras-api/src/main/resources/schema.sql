DROP TABLE IF EXISTS tbl_agenda_transacao;
CREATE TABLE tbl_agenda_transacao (
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	id_tipo_transacao INT NOT NULL,
	id_status_transacao INT NOT NULL,
	id_cta_origem INT NOT NULL,
	id_cta_destino INT NOT NULL,
	vl_taxa DECIMAL NOT NULL,
	dt_transferencia DATE NOT NULL,
	dt_agendamento DATE NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tbl_tipo_transacao;
CREATE TABLE tbl_tipo_transacao (
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	sigla varchar NOT NULL,
	desc_tipo_transacao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tbl_cta_corrente;
CREATE TABLE tbl_cta_corrente (
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	cta varchar NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tbl_log_processamento_transacao;
CREATE TABLE tbl_log_processamento_transacao (
	id INT NOT NULL AUTO_INCREMENT UNIQUE,
	id_agenda_transacao INT NOT NULL,
	mensagem varchar NOT NULL,
	id_tipo_log INT NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tbl_tipo_log;
CREATE TABLE tbl_tipo_log (
	id INT NOT NULL AUTO_INCREMENT,
	desc varchar NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE tbl_agenda_transacao ADD CONSTRAINT tbl_agenda_transacao_fk0 FOREIGN KEY (id_status_transacao) REFERENCES tbl_tipo_transacao(id);

ALTER TABLE tbl_agenda_transacao ADD CONSTRAINT tbl_agenda_transacao_fk1 FOREIGN KEY (id_cta_origem) REFERENCES tbl_cta_corrente(id);

ALTER TABLE tbl_agenda_transacao ADD CONSTRAINT tbl_agenda_transacao_fk2 FOREIGN KEY (id_cta_destino) REFERENCES tbl_cta_corrente(id);

ALTER TABLE tbl_log_processamento_transacao ADD CONSTRAINT tbl_log_processamento_transacao_fk0 FOREIGN KEY (id_agenda_transacao) REFERENCES tbl_agenda_transacao(id);

ALTER TABLE tbl_log_processamento_transacao ADD CONSTRAINT tbl_log_processamento_transacao_fk1 FOREIGN KEY (id_tipo_log) REFERENCES tbl_tipo_log(id);
