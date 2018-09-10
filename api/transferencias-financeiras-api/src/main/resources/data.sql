insert into tbl_cta_corrente (cta) values ('111111');
insert into tbl_cta_corrente (cta) values ('222222');

insert into tbl_agenda_transacao (tipo_transacao, status_transacao, id_cta_origem, id_cta_destino, vl_taxa, dt_transferencia , dt_agendamento) values ('C', 'AT', 1, 2, '10.01', NOW(), NOW()+1);
