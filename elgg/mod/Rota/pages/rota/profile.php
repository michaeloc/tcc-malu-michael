<?php
/*
 * Create by Michael and Malu
 * Rescue data about adm...
 */
$user = elgg_get_logged_in_user_entity();


$title = "My first page";

//echo $title;

$options = array(
		'type' => 'object',
		'subtype' => 'blog',
		'owner_guid' => $user->guid,
		'count' => true,);

$num_blogs = elgg_get_entities($options);


$params = array('name' => $user->name,'email' => $user-> email,'num_blogs' => $num_blogs);

$saida = implode("#",$params);

echo $saida;



