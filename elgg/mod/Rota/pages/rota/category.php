<?php
/*
 * Lista as rotas
 */

$category = get_input('category','getting_started');

$title = elgg_echo('rota:title:');

elgg_push_breadcrumb(elgg_echo('rota'), 'rota');

elgg_push_breadcrumb($title);

$options = array(
		'type'=>'object',
		'subtype'=>'rota',
		'full_view'=>true,
		'limit' =>2,
		'list_class'=>'rota-list',);

$content = elgg_list_entities_from_metadata($options);

$params = array(
		'content'=>$content,
		'title' => $title,
		'filter'=>false,);

$body = elgg_view_layout('content', $params);

echo elgg_view_page($title,$body);