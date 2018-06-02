var countOfFields = 1; // Текущее число полей
var maxFieldLimit = 6; // Максимальное число возможных полей
butAdd = document.getElementById('addfields');
butAdd.addEventListener('click', addField, false);
/**
* Пока этот метод не используется, но в дальнейшем пригодится
*/
function deleteField(a)
 {
	 var contDiv = a.parentNode;
	 contDiv.parentNode.removeChild(contDiv);
	 countOfFields--;
	 // Возвращаем false, чтобы не было перехода по сслыке
 return false;
}
    
function addField() 
{
	 // Проверяем, не достигло ли число полей максимума
	 if (countOfFields >= maxFieldLimit)
	  {
		 alert("Число полей достигло своего максимума = " + maxFieldLimit);
		 return false;
	 }
	 var object = document.getElementById("new_fields");
	 object.style.display = 'block' 
	 var tr_text = document.createElement("tr");
	 tr_text.innerHTML = '<td><input name="name_' + countOfFields + '" type="text" /></td><td><input name="isRight_' + countOfFields + '" type="checkbox"></td>';
	 object.appendChild(tr_text);
	 countOfFields++;// Увеличиваем текущее значение числа полей
 return false;
}
