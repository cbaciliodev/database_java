import { Component, OnInit } from '@angular/core';
import { Tema } from 'src/app/models/tema.model';
import { Actividad } from 'src/app/models/actividad.model';
import { CargoService } from 'src/app/services/service.index';
import swal from 'sweetalert'
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-update-tema',
  templateUrl: './update-tema.component.html',
  styleUrls: ['./update-tema.component.css']
})
export class UpdateTemaComponent implements OnInit {

  public _actividad = new Actividad(null, '', null);
  public _tema = new Tema(null, '', '', this._actividad);

  public _lista_temas: Tema[];
  public _lista_actividades: Actividad[];

  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router,
    public _activateRouter: ActivatedRoute) {

    _activateRouter.params.subscribe(params => {
      let id = params['id'];
      if (id !== 'nuevo') {
        this.actualizar = true;
        this.cargarTema(id);
      }else{
        this._lista_actividades=[];
      }
    })

  }

  ngOnInit() {
    
    this.cargarListaActividades();
  }


  valor(s: any) {
    console.log(s.target.value, ' valor')
  }
  cargarTema(id: number) {

    this._serviceCargo.findOneTemaById(id)
      .subscribe(tema => {
        this._tema = tema

        console.log(this._tema);
      });
  };

  cargarListaActividades() {


    this._serviceCargo.findAllActividades()
      .subscribe(actividades => {
        this._lista_actividades = actividades
      });
  };



  guardarDocente(f: NgForm, s: string) {

    console.log(f.value)
    console.log(this._tema)

    if (f.invalid) {
      return;
    }

    if (s == 'create') {

      this._serviceCargo.createTemas(this._tema)
        .subscribe(data => {
          swal(`Docente Creado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/tema'])
        });
    }

    if (s == 'update') {
      this._serviceCargo.updateTemas(this._tema, this._tema.icod_TEMA)
        .subscribe(data => {
          swal(`Docente Actualizado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/tema'])
        });
    }
  }
}
