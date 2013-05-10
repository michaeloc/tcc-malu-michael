<?php
/*
 * Type: object subtype: rota
 */

$item = $vars['entity'];

$question = $item->title;

$answer = $item->description;

if($vars['full_view']){
	
	$body = elgg_view('output/text',array(
			'value'=>$answer,
			'class'=>'mtn',));

echo <<< HTML
<div class = "rota-item" id ="$item->guid">
	<h2> $question </h2>
	$body
</div>
HTML;
}