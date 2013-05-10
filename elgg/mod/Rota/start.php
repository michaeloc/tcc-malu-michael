<?php

elgg_register_event_handler('init','system','rota_init');

function rota_init()
{
	elgg_register_page_handler('rota', 'rota_page_handler');
	
	$base = elgg_get_plugins_path().'Rota/actions/rota';
	
	elgg_register_action("rota/save", "$base/save.php","admin");
	
	$lib = elgg_get_plugins_path().'Rota/lib/rota.php';
	
	elgg_register_library('rota',$lib);
	
	elgg_load_library('rota');
	
	$item = new ElggMenuItem('rota',elgg_echo('rota'),'rota'); //identificador, texto e referência
	
	$item2 = new ElggMenuItem ('endereco',elgg_echo('search'),'endereço');
	
	elgg_register_menu_item('site',$item);// site é o nome do menu
	
	elgg_register_menu_item('site',$item2);
		
	
}
function rota_page_handler($page)
{
	$pages_dir = elgg_get_plugins_path().'Rota/pages/rota';
	
	if(count($page) == 0){
		$page[0] = 'index';
	}
	switch($page[0]){
		case 'writeRota':
			require "$pages_dir/index.php";
			break;
		case 'category':
			set_input('category',$page[1]);
			require "$pages_dir/category.php";
			break;
		case 'searchRota':
			require "$pages_dir/search.php";
		default:
			
			break;
	}
	
	
	return true;
}
