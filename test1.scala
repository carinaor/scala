object test1 {
  var matriz = Array(
	Array(0,1,-1),
	Array(1,0,-1),
	Array(1,1,1)
);                                                //> matriz  : Array[Array[Int]] = Array(Array(0, 1, -1), Array(1, 0, -1), Array(1
                                                  //| , 1, 1))
var inicio_salida = 0;                            //> inicio_salida  : Int = 0
var pos = Array(inicio_salida,0);                 //> pos  : Array[Int] = Array(0, 0)

var obstruccion = -1;                             //> obstruccion  : Int = -1
var pasa = 0;                                     //> pasa  : Int = 0
var colecciona = 0;                               //> colecciona  : Int = 0

var end = 0;                                      //> end  : Int = 0

def adelante(pos: Array[Int]): Array[Int] = { return Array(pos(0), pos(1)+1) }
                                                  //> adelante: (pos: Array[Int])Array[Int]

def atras(pos: Array[Int]): Array[Int] = { return Array(pos(0), pos(1)-1) }
                                                  //> atras: (pos: Array[Int])Array[Int]

def subir(pos: Array[Int]): Array[Int] = { return Array(pos(0)-1, pos(1)) }
                                                  //> subir: (pos: Array[Int])Array[Int]

def bajar(pos: Array[Int]): Array[Int] = { return Array(pos(0)+1, pos(1)) }
                                                  //> bajar: (pos: Array[Int])Array[Int]

def getNewPosValue(newPos: Array[Int]): Int = {
  var arr: Int = matriz(newPos(0))(newPos(1))
	return arr
}                                                 //> getNewPosValue: (newPos: Array[Int])Int

var i = inicio_salida;                            //> i  : Int = 0
do {
  i = i + 1
  println(s"Travel nº $i")
  
  var cell = Map(
	  "next_pos" -> adelante(pos),
	  "down_pos" -> bajar(pos)
  )
  
  if(cell("next_pos")(1)<matriz.length || cell("down_pos")(0)<matriz.length){
  
	  if(cell("next_pos")(1)<matriz.length && getNewPosValue(adelante(pos)) !=obstruccion){
	    
			pos = adelante(pos)
			println(s"Move forward 1 step! New pos (${pos(0)}),(${pos(1)}) Call Val ${ matriz(pos(0))(pos(1)) }")
	  
	  }else if(cell("down_pos")(0)< matriz.length && getNewPosValue(bajar(pos))!=obstruccion){
			pos = bajar(pos)
			println(s"Move down 1 step! New pos (${pos(0)}),(${pos(1)}) Call Val ${ matriz(pos(0))(pos(1))}")
	  }else{
			// exit while loop if there's no valid path
			end = 1
	  }
	  
	  if(matriz(pos(0))(pos(1))==1){
			colecciona = colecciona + 1
      println("Pick up!")
			matriz(pos(0))(pos(1)) = 0
	  }
	  
  }else{ end = 1 }
  
} while (i < matriz.length * matriz(0).length && end != 1);
                                                  //> Travel nÂº 1
                                                  //| Move forward 1 step! New pos (0),(1) Call Val 1
                                                  //| Pick up!
                                                  //| Travel nÂº 2
                                                  //| Move down 1 step! New pos (1),(1) Call Val 0
                                                  //| Travel nÂº 3
                                                  //| Move down 1 step! New pos (2),(1) Call Val 1
                                                  //| Pick up!
                                                  //| Travel nÂº 4
                                                  //| Move forward 1 step! New pos (2),(2) Call Val 1
                                                  //| Pick up!
                                                  //| Travel nÂº 5

println(s"Picked up: $colecciona")                //> Picked up: 3
for(m <- matriz){
	var str = ""
	for(m2 <- m){
	str = str + s" $m2"
	}
	println(s" ( $str ) ")
}                                                 //>  (  0 0 -1 ) 
                                                  //|  (  1 0 -1 ) 
                                                  //|  (  1 0 0 ) 

println("Now it returns...")                      //> Now it returns...

i = inicio_salida // Reset
end = 0 // Reset

do {
  i = i + 1
  println(s"Travel nº $i")
	
  var cell2 = Map(
	  "prev_pos" -> atras(pos),
	  "up_pos" -> subir(pos)
  )

  if(cell2("prev_pos")(1) >= 0 || cell2("up_pos")(0) >= 0){

	  if(cell2("prev_pos")(1) >=0 && getNewPosValue(atras(pos)) != obstruccion){
			pos = atras(pos)
			println(s"Move back 1 step! New pos (${pos(0)}),(${pos(1)}) Call Val ${matriz(pos(0))(pos(1))}")
	
	  }else if(cell2("up_pos")(0)>=0 && getNewPosValue(subir(pos)) != obstruccion){
			pos = subir(pos)
			println(s"Move up 1 step! New pos (${pos(0)}),(${pos(1)}) Call Val ${matriz(pos(0))(pos(1))}")
	
	  }else{
			// exit while loop if there's no valid path
			end = 1
	  }
	  
	  if(matriz(pos(0))(pos(1)) == 1){
			colecciona= colecciona + 1
			println("Pick up!")
			matriz(pos(0))(pos(1)) = 0
	  }
  }else{
  	end = 1
  }
  
} while (i < matriz.length * matriz(0).length && end != 1);
                                                  //> Travel nÂº 1
                                                  //| Move back 1 step! New pos (2),(1) Call Val 0
                                                  //| Travel nÂº 2
                                                  //| Move back 1 step! New pos (2),(0) Call Val 1
                                                  //| Pick up!
                                                  //| Travel nÂº 3
                                                  //| Move up 1 step! New pos (1),(0) Call Val 1
                                                  //| Pick up!
                                                  //| Travel nÂº 4
                                                  //| Move up 1 step! New pos (0),(0) Call Val 0
                                                  //| Travel nÂº 5
                                                 
println(s"Picked up: $colecciona");               //> Picked up: 5
for(m <- matriz){
	var str = ""
	for(m2 <- m){
	str = str + s" $m2"
	}
	println(s" ( $str ) ")
}                                                 //>  (  0 0 -1 ) 
                                                  //|  (  0 0 -1 ) 
                                                  //|  (  0 0 0 ) 

}