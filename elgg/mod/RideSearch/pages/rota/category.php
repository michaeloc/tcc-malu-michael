<?php
/*
 * Lista as rotas
 */



$user = elgg_get_logged_in_user_entity();

$content = new ElggObject();

$options = array(
		'type'=>'object',
		'subtype'=>'rota',
		'owner_guid' => $user->guid,
		//'full_view'=>true,
		//'limit' =>1,
		//'list_class'=>'rota-list',
		'count'=>'true',

		);

/*
*
* Função elgg_list_entities_from_metadata_rotaSearch
* criada para imprimir a saida e o destino das rotas...
*/


$content = elgg_list_entites_from_metadata_rotaSearch($options); 





echo $content;

