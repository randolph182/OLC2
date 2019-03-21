funcion ReporteAritmetico(){
	var contenido = CrearArrayDesdeArchivo("VentanaAritmetica.gdato");
	var TodoCorrecto = contenido.filtrar(RevisionAritmetica);
	Imprimir("Los estudiantes que ganaron la evaluacion aritmetica son:");
	Imprimir("Verificar que esten ordenados alfabeticamente");
	TodoCorrecto.map(ImprimirGanadores);
	Imprimir("Verificar que esten ordenados alfabeticamente invertido");
	TodoCorrecto.Invertir();
	TodoCorrecto.map(ImprimirGanadores);
}

funcion ReporteHistorico(){
	var contenido = CrearArrayDesdeArchivo("VentanaHistoria.gdato");	
	var TodoCorrecto = contenido.filtrar(RevisionHistoria);
	Imprimir("El primer alumno en contestar todo buen fue ");
	var primero = TodoCorrecto.Buscar(BuscarHistoria);
	Imprimir(primero.CTNombre);
    Imprimir("Todos Los estudiantes que ganaron la evaluacion de historia son: (Orden Descendente)");
    TodoCorrecto.Descendente();
	TodoCorrecto.map(ImprimirGanadores);
}

funcion ReporteIngles(){
	var contenido = CrearArrayDesdeArchivo("VentanaIngles.gdato");
	Imprimir("¿Algun estudiante contesto mal el nombre del algoritmo ackermanm? " + contenido.Alguno(NombreAckerman));
	Imprimir("¿Todos los estudiantes contestaron correctamente Ackermann(3,11)? " + contenido.Todos(CalculoAckerman));
}

funcion ReporteLogico(){
	var contenido = CrearArrayDesdeArchivo("VentanaLogica.gdato");
	Imprimir("Imprimiendo nombres de los estudiantes que saben par e impar"); 
	var concatenacion = contenido.Reduce(EstudiantesParidad);
}

funcion RevisionAritmetica(var item){
	retornar item.CPotencia == 3125 && item.CFactorial == 5040 && item.CInvertido == 743032153 && item.CFibonacci == 4181;
}

funcion RevisionHistoria(var item){
	retornar item.CDPaisaje1 == "Playa" && item.CDPaisaje2 == "Luna" && item.CDPaisaje3 == "Selva" && item.CDPaisaje4 == "Desierto" && item.CDPaisaje5 == "Oceano";
}

funcion BuscarHistoria(var item){
	retornar item.CDPaisaje1 == "Playa" && item.CDPaisaje2 == "Luna" && item.CDPaisaje3 == "Selva" && item.CDPaisaje4 == "Desierto" && item.CDPaisaje5 == "Oceano";
}

funcion ImprimirGanadores(var item){
	Imprimir(item.CTNombre);
}

funcion NombreAckerman(var item){
	retornar item.CTPregunta != "Ackermann";
}

funcion CalculoAckerman(var item){
	retornar item.CAckerman == 16381;
}

funcion EstudiantesParidad(var total, var item){
	Si(item.CTPar == "Par" && item.CTImpar == "Impar"){
		retornar total + item.CTNombre;
	}sino{
		retornar total + "";
	}
}

