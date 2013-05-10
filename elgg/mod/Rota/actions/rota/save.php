<?php
/*
 * Action
 */
$saida = get_input('question'); //obtendo os valores dos formulários

$destino = get_input('answer');

$save = rota_save_topic($saida, $destino);

if(!$save){
	register_error(elgg_echo('rota:error:no_save'));
	
}else{
	system_message(elgg_echo('rota:status:save'));
}

forward(REFERER);

