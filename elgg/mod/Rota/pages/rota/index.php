<?php
/**
 * Rota main index
 */

$title = elgg_echo('rota:categories');

$content = elgg_view_title('$title');

$content = elgg_view_form('rota/save');

$body = elgg_view_layout('one_column',array('content'=>$content));

echo elgg_view_page($title,$body);
