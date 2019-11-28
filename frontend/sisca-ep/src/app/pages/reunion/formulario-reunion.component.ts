import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert'
import { Reunion } from 'src/app/models/reunion.model';
import { CargoService } from 'src/app/services/service.index';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-formulario-reunion',
  templateUrl: './formulario-reunion.component.html',
  styleUrls: ['./formulario-reunion.component.css']
})
export class FormularioReunionComponent implements OnInit {

  public _reunion = new Reunion(null, '', '', '', '', '');

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
        //this.cargarReunion(id);
      }
    })

  }

  ngOnInit() {
  }


  valor(s: any) {
    console.log(s.target.value, ' valor')
  }

  cargarReunion(id: number) {

    this._serviceCargo.findOneDocenteById(id)
      .subscribe(reunion => {
        this._reunion = reunion
      })
  };

  guardarReunion(f: NgForm, s: string) {

    if (f.invalid) {
      return;
    }

    console.log(f.value)
    if (s == 'create') {

      this._serviceCargo.createReunion(this._reunion)
        .subscribe(data => {
          swal(`Reunion Creado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/listaReunion'])
        });
    }

    /* if (s == 'update') {
      this._serviceCargo.updateDocente(this._docente, this._docente.icod_DOCENTE)
        .subscribe(data => {
          swal(`Docente Actualizado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/docente'])
        });
    } */
  }

}
