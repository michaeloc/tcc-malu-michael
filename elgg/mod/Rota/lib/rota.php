<?php
/*
 * libraries
 */
function rota_save_topic($saida, $destino){
	
	$rota = new ElggObject();
	$rota->subtype = 'rota';
	$rota->description = $destino;
	$rota->title = $saida;
	
	
	$guid = $rota->save();
	
	if(!$guid){
		return false;
	}
	return true;
}
