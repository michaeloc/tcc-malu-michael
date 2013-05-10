<?php
/*
 * rota/save form body
 */

$rota_input_saida = elgg_view("input/text", array('name'=>'question'));

$rota_input_destino = elgg_view("input/text", array('name'=>'answer'));

$button = elgg_view("input/submit", array('value'=>elgg_echo('save')));

echo <<< HTML

<div>
	<label>Saida</label><br/>
	$rota_input_saida
</div>

<div>
	<label>Destino</label><br/>
	$rota_input_destino
</div>

<div class ="elgg_foot">
	$button
</div>
HTML;
?>