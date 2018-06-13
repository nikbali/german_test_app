var countOfFields = 1; // Текущее число полей
var maxFieldLimit = 6; // Максимальное число возможных полей
var num = 1; //текущий номер ответа
butAdd = document.getElementById('addfields');
butAdd.addEventListener('click', addField, false);


function deleteField(a)
 {
		
		 var container = document.getElementById('answer'+a.id);
		 container.remove();
		 countOfFields--;
 return false;
}
    
function addField() 
{
	 if (countOfFields > maxFieldLimit) 
	 {
		 alert("Count of fields reached max value = " + maxFieldLimit);
		 return false;
	 }
	 var object = document.getElementById("answers");
	 object.style.display = 'block' 
	 
	 var parent1 = document.createElement("div");
	 parent1.className = "col-sm-4";
	 parent1.setAttribute('id','answer'+num);

	 
	 var parent2 = document.createElement("div");
	 parent2.className = "panel panel-default";
	 
	 var parent3 = document.createElement("div");
	 parent3.className = "panel-heading";
	 
	 parent3.innerHTML = '<h3 class="panel-title">Answer '+ num +'</h3>';
	 
	 var panel_body = document.createElement("div");
	 panel_body.className = "panel-body";
	 panel_body.innerHTML = '<input name="name_' + num + '"class="form-control" type="text" placeholder="Input text..."><br>';
	 
	 
	 var form_check = document.createElement("div");
	 form_check.className = "form-check";
	 form_check.innerHTML = '<label class="form-check-label" for="isRight">Is Right?</label><input class="form-check-input" type="checkbox" name="isRight_' + num + '"><button type="button" id = "'+num+'" onclick=\"return  deleteField(this)\" class="btn btn-xs btn-default">Remove</button>';
	 
	 panel_body.appendChild(form_check);
	 
	 parent2.appendChild(parent3);
	 parent2.appendChild(panel_body);	 
	 parent1.appendChild(parent2);

	 object.appendChild(parent1);
	 countOfFields++;// Увеличиваем текущее значение числа полей
	 num++; //увеличиваем текущий номер вопрса
	 return false;
}

