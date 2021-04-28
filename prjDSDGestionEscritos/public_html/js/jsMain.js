function f_ColocarImagenWord(indicadorImagen) {

    if (indicadorImagen ==1)
       return '/imagenes/btn_word.png';
    else if(indicadorImagen ==2) 
       return '/imagenes/btn_word_m.png';
    else
       return '';
    
}

function f_CountLeft(field, count, max) {
    if (document.getElementById(field).value.length > max)
        document.getElementById(field).value = document.getElementById(field).value.substring(0, max);
    else 
        document.getElementById(count).value = max - document.getElementById(field).value.length;
}

function f_valida_datos() {
    var vcAnioResolucion = document.getElementById('frmNuevaResolucion:txtAnioResolucion').value;
    mNuError = 0;
    var vcMensaje = "Por favor verifique lo siguiente:\n";
    //Nro Resolución
    if (document.getElementById('frmNuevaResolucion:txtNroResolucion').value.length <= 0) {
        mNuError++;
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'red');
        vcMensaje += "-Debe ingresar el número de resolución.\n"
    } else {
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'black');
    }
    
    //Anio Resolución
    if (vcAnioResolucion<1990) {
        mNuError++;
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'red');
        vcMensaje += "-Debe ingresar un año de resolución válido.\n"
    } else {
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'black');
    }
    
    //Sigla Resolución
    
    //Fecha Resolución
    if (document.getElementById('frmNuevaResolucion:clFechaResolucionInputDate').value.length <= 0) {
        mNuError++;
        f_colorea('frmNuevaResolucion', 'FechaResolucion', 'red');
        vcMensaje += "-Debe ingresar una fecha válida.\n"
    } else {
        f_colorea('frmNuevaResolucion', 'FechaResolucion', 'black');
        //El año de resolución debe coincidir con la fecha de resolución
        var vFechaResolucion = document.getElementById('frmNuevaResolucion:clFechaResolucionInputDate').value;
        if(vFechaResolucion.substring(6,vFechaResolucion.length) == vcAnioResolucion){
            f_colorea('frmNuevaResolucion', 'FechaResolucion', 'black');
        }else{
            vcMensaje += "-El año de la resolución debe coincidir con el año de la fecha de resolución.\n"
            mNuError++;
            f_colorea('frmNuevaResolucion', 'FechaResolucion', 'red');
        }
    }
    
    //Sumilla
    if (document.getElementById('frmNuevaResolucion:txtSumilla').value.length <= 0) {
        mNuError++;
        vcMensaje += "-Debe ingresar una sumilla.\n"
        f_colorea('frmNuevaResolucion', 'Sumilla', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'Sumilla', 'black');
    }
    
    //Partes
    if (document.getElementById('frmNuevaResolucion:txtPartes').value.length <= 0) {
        mNuError++;
        vcMensaje += "-Debe ingresar las partes de la resolución.\n"
        f_colorea('frmNuevaResolucion', 'Partes', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'Partes', 'black');
    }
    
    //Nro Expediente
    var table = document.getElementById("frmNuevaResolucion:tblExpedientes");
    var tamanio=0;
    for (var i = 0, row; row = table.rows[i]; i++) {
       tamanio = i;
    }

    if (tamanio<2) {
        mNuError++;
        vcMensaje += "-Debe ingresar al menos un expediente.\n"
        f_colorea('frmNuevaResolucion', 'Expedientes', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'Expedientes', 'black');
    }
    
    //Resolución
    table = document.getElementById("frmNuevaResolucion:tblDocumentos");
    tamanio=0;
    for (var i = 0, row; row = table.rows[i]; i++) {
       tamanio = i;
    }

    if (tamanio<2) {
        mNuError++;
        vcMensaje += "-Debe adjuntas el documento de la resolución.\n"
        f_colorea('frmNuevaResolucion', 'DocResolucion', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'DocResolucion', 'black');
    }
    
    if(mNuError>0)
        alert(vcMensaje);
    
    return mNuError;
}

function f_moveNext() {
    if (f_valida_datos() == 0)
            if (!confirm('¿Está seguro de registrar la resolución?', 'INDECOPI - RESOLUCIONES INDECOPI'))
                return false;
            else 
                return true;
    else {
        //alert('Por favor, verifique y complete los datos marcados en color rojo.');
        return false;
    }
    return true;
}

function f_colorea(mFormulario, mElementId, mTipo) {
    document.getElementById(mFormulario + ':lbl' + mElementId).style.color = mTipo;
}

function fn_validaExpediente(){
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vNroExp = document.getElementById('formExpediente:txtNroExp').value;
    var vAnioExp = document.getElementById('formExpediente:txtAnioExp').value;
    //var vSiglaExp = document.getElementById('formExpediente:cboSiglaExpedientecomboboxField').value;
    
    //Validar Número de expediente
    if(vNroExp.length > 0){
     f_colorea('formExpediente','NroExpediente','Black');
    }else{
     vMensaje+= '-Debe ingresar el número de expediente.\n'; 
     nuError += 1;
     f_colorea('formExpediente','NroExpediente','Red');
    }
    //Validar Año de expediente 
    if(vAnioExp.length > 0){
     f_colorea('formExpediente','NroExpediente','Black');
    }else{
     vMensaje+= '-Debe ingresar el año del expediente.\n'; 
     nuError += 1;
     f_colorea('formExpediente','NroExpediente','Red');
    }
    //Validar Sigla de expediente
    /*if(vSiglaExp.length > 0){
     f_colorea('formExpediente','NroExpediente','Black');
    }else{
     vMensaje+= '-Debe ingresar la sigla del expediente.\n'; 
     nuError += 1;
     f_colorea('formExpediente','NroExpediente','Red');
    }*/
    if(nuError > 0){
        alert(vMensaje);
        return false;
    }else{
        return true;
    }
}

function Valida_Dato(e, opc) {
                //Segun la opcion se habilita o no el espacio
                tecla = (document.all) ? e.keyCode : e.which;
                if (tecla == 8 || tecla == 0)
                    return true;//Tecla de retroceso (para poder borrar) 
                switch (opc) {
                    case 1:
                    //Para numeros enteros en general. Solicitudes, cedulas,nit, numeros de identificacion, etc
                        patron = /[1234567890-]/;//solo numeros y lineas
                        break;

                    case 2:
                    //Para texto largo general
                        if (tecla==225 || tecla==233 || tecla==237 || tecla==243 || tecla==250 || 
                            tecla==193 || tecla==201 || tecla==205 || tecla==211 || tecla==218) return true;
                        if (tecla == 32)
                            return true;//Tecla de espacio
                        patron = /[a-zA-ZÑñ1234567890,.()-ÁÉÍÓÚáéíóú]/;//caracteres y numeros
                        patron = /\w/;//caracteres y numeros
                        break;

                    case 3:
                    //Para numeros telefonicos 
                        if (tecla == 32)
                            return true;//Tecla de espacio
                    //    patron = /[EXText1234567890.()-]/;
                          patron = /[EXTextAaNnOo1234567890.()-]/;
                        break;

                    case 4:
                    //para campos que necesitan valores numericos de precios 
                        patron = /[1234567890.,]/;//solo numeros puntos y comas
                        break;

                    case 6:
                    //Para validar campos de nombres y apellidos
                        if (tecla==225 || tecla==233 || tecla==237 || tecla==243 || tecla==250 || 
                            tecla==193 || tecla==201 || tecla==205 || tecla==211 || tecla==218) return true;
                        if (tecla == 32)
                            return true;//Tecla de espacio
                        patron = /[a-zA-ZÑñ]/;
                        break;

                    case 7:
                    //Para validar email
                        patron = /[a-zA-ZÑñ1234567890@.-_]/;
                        break;

                    case 8:
                    //Para números enteros positivos DNI
                        patron = /[1234567890]/;//solo numeros enteros positivos
                        break;

                    case 9:
                    //Direcciones 
                        if (tecla==225 || tecla==233 || tecla==237 || tecla==243 || tecla==250 || 
                            tecla==193 || tecla==201 || tecla==205 || tecla==211 || tecla==218) return true;
                        if (tecla == 32)
                            return true;//Tecla de espacio
                        patron = /[a-zA-ZÑñ1234567890.(),-]/;
                        break;

                    case 10:
                    //Caracteres y números
                        if (tecla == 32)
                            return true;
                        patron = /[a-zA-ZÑñ1234567890]/;
                        break;
                    case 11:
                    //Caracteres
                        patron = /[abcdefghijklmnñopqrstuvxyzwAZBCDEFGHIJKLMNOPQRSTUVWXYÑñÁÉÍÓÚáéíóú]/;//caracteres
                        break;                            
                    case 12:
                    //para campos que necesitan valores numericos de precios 
                        patron = /[1234567890.]/;//solo numeros puntos y comas
                        break;
                        
                    default: 
                        return true;    
                }
                te = String.fromCharCode(tecla);
                return patron.test(te);
            }
            
function seleccionarTodoDoc(source) {
    checkboxes = document.getElementsByTagName('input');
    for (i = 0;i < checkboxes.length;i++)
    {
        if (checkboxes[i].type == "checkbox" && checkboxes[i].id != "frmConversion:tbDocProcesados:cbxSelTodosDoc")
        {
            checkboxes[i].checked = source.checked;
        }
    }
}


function isPresDayEnabled(day) {  
    
    var curDt = new Date();

    
    var prvDt = new Date();

    prvDt.setDate(prvDt.getDate() - 8); // cambio temporal

    
    var slcDt = new Date(day.date);
     
        return (slcDt.getTime() < curDt.getTime());

}

function getPresDisabledStyle(day) {
    if (!isPresDayEnabled(day)){
        return 'rich-calendar-boundary-dates disabledDay';
    }

}

function check(e) {
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla == 8) {
        return true;
    }

    // Patron de entrada, en este caso solo acepta numeros y letras
    patron = /[A-Za-z0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}