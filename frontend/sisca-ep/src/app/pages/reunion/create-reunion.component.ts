import { Component, OnInit } from '@angular/core';
import { Reunion } from 'src/app/models/reunion.model';
import { CargoService } from 'src/app/services/service.index';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import swal from 'sweetalert'

@Component({
  selector: 'app-create-reunion',
  templateUrl: './create-reunion.component.html',
  styleUrls: ['./create-reunion.component.css']
})
export class CreateReunionComponent implements OnInit {


  public _reunion = new Reunion(null, '', '', '', '', '');
  public _listaReuniones: Reunion[];


  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router,
    public _activateRouter: ActivatedRoute) {
    this._listaReuniones = [];

    _activateRouter.params.subscribe(params => {
      let id = params['id'];
      if (id !== 'nuevo') {
        this.actualizar = true;
      }
    })

  }

  ngOnInit() {
    this.findAllReunion();
  }

  findAllReunion() {

    this._serviceCargo.findAllReunionesDocentes()
      .subscribe(res => {
        console.log(res)
        if (res == null) {
          this._listaReuniones = [];
          return;
        }
        this._listaReuniones = res;
      })
  }

  valor(s: any) {
    console.log(s.target.value, ' valor')
  }

  guardarDocente(f: NgForm, s: string) {

    if (f.invalid) {
      return;
    }

    if (s == 'create') {



      this._serviceCargo.createReunion(this._reunion)
        .subscribe(data => {
          swal(`Docente Creado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/docente'])
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

  deleteDocente(i :number){

  }
}


