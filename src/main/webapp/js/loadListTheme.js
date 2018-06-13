var xhr = new XMLHttpRequest();
var object_test;
var thematics = [];
butAdd = document.getElementById('btn_theme');
//butAdd.addEventListener('click', loadListTheme, false);

function loadListTheme(){
try
{
   
      var url = "/querytheme?test_id=1"; 
      xhr.open('GET', url);
     xhr.onload = function (e) 
	 {
		  if (xhr.status == 200)
		  { 
				object_test = JSON.parse(xhr.responseText);
				thematics = object_test.thematics;
				
				var list = document.getElementById('list_theme');
				// очистка списка
				while (list.firstChild) 
				{
				list.removeChild(list.firstChild);
				}           
				// заполнение списка
				for (var i = 0; i < thematics.length; i++)
				{
					var element = document.createElement('li');
					element.innerHTML = '<a href="#">'+thematics[i].name +'</a>';
					list.appendChild(element);           
				} 					
		  }
	 };	  
	xhr.send(null);
}
catch(e)
{
    alert('Error load list thematics'); 
    document.location.href ='/index.html';
}
}