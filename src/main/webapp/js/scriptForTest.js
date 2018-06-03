var xhr = new XMLHttpRequest();
var objects = [];
var cnt = 0;

var question_new = [];
var yourAns = new Array;
var score = 0;
var curent = 0;


function loadData(){
try
{
    
      var url = "http://nikbali.ru/queryquestion?rand=20"; 
      xhr.open('GET', url, false);
      xhr.send();
      if (xhr.status == 200)
      { 
        objects = JSON.parse(xhr.responseText);
      }
    
}
catch(e)
{
    alert('Error load data from Internet'); 
    document.location.href ="http://nikbali.ru/";
}
}
function Engine(question, answer) {yourAns[question]=answer;}
function Score(){
   var answerText = "Results:\n";
   for(var i = 0; i < yourAns.length; ++i)
   {
      var num = i+1;
      answerText=answerText+"\n    Question: "+ num +"";
      var true_ans = "";
      var number_ans;
      //поиск правильного ответа в вопросе
      for(var j=0; j<objects[i].answers.length; j++)
      {
          if(objects[i].answers[j].isRight == true) 
          {
            true_ans = objects[i].answers[j].text;
            number_ans = j;
          }
      }
      
      if(true_ans=="")
      {
          answerText=answerText+"\n    Answer true: " + " Error In Data\n";
      }
      else
      {
          if(yourAns[i] == number_ans)
          {
            answerText=answerText+": True! \n";
            ++score;
          }
          else
          {
            answerText=answerText+"\n    Answer true: " +true_ans +"\n";
          }
      }
   }

   answerText=answerText+"\nYour score: "+score+"\n";

   alert(answerText);
   yourAns = [];
   score = 0;
   document.location.href ="http://nikbali.ru/";
}


function nextQuestion(){
    if(curent == objects.length)
    {
        Score();
    }
    else
    {
       var sp1 = document.createElement("div"); //тут все дело храним
       sp1.setAttribute("id", "process");
       
            
       var question = objects[curent];
       sp1.innerHTML = '<li value="' + (curent+1) +'"><span class="quest">' + question.textOfQuestion + '</span><br/>';
       /**
       *Цикл по всем ответам в value храним номер ответа который выбрал пользователь
       */
       for(var i in question.answers)
       {
            sp1.innerHTML +='<input type=radio name="q' + curent + '" value="' + i +
            '" onClick="Engine(' + curent + ', this.value)">' + question.answers[i].text + '<br/>';
       }               
       var sp2 = document.getElementById("process");
       while (sp2.firstChild) 
       {
            sp2.removeChild(sp2.firstChild);
       }
       sp2.appendChild(sp1);
       curent++;
    }
}




