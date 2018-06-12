var countOfFields = 1; // Текущее число полей
var maxFieldLimit = 6; // Максимальное число возможных полей
butAdd = document.getElementById('addfields');
butAdd.addEventListener('click', addField, false);

/**
* Пока этот метод не используется, но в дальнейшем планирую 
**/
function deleteField(a)
 {
		 var contDiv = a.parentNode;
		 contDiv.parentNode.removeChild(contDiv);
		 countOfFields--;
 return false;
}
    
function addField() 
{
	 if (countOfFields >= maxFieldLimit) 
	 {
		 alert("Count of fields reached max value = " + maxFieldLimit);
		 return false;
	 }
	 var object = document.getElementById("new_fields");
	 object.style.display = 'block' 
	 var tr_text = document.createElement("tr");// Создаем элемент ДИВ
	 // Добавляем HTML-контент с пом. свойства innerHTML
	 tr_text.innerHTML = '<td><input name="name_' + countOfFields + '" type="text" /></td><td><input name="isRight_' + countOfFields + '" type="checkbox"></td>';
	 object.appendChild(tr_text);
	 countOfFields++;// Увеличиваем текущее значение числа полей
	 return false;
}
