import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert'
import { Docente } from 'src/app/models/docente.model';
import { CargoService } from 'src/app/services/service.index';
import { Router, ActivatedRoute } from '@angular/router';
import { Cargo } from 'src/app/models/cargo.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-docente',
  templateUrl: './docente.component.html',
  styleUrls: ['./docente.component.css']
})
export class DocenteComponent implements OnInit {

  public _listaDocente: Docente[];
  public cantidad: number;
  public p: number = 1;
  public paginador: any;

  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router,
    public _activateRouter: ActivatedRoute) {

    _activateRouter.params.subscribe(params => {
      let id = params['id'];
      if (id !== 'nuevo') {
        console.log(id);
        this.actualizar = true;
        //this.cargarCargo(id);
      }
    })

  }

  ngOnInit() {
    this.findAllDocentes();
  }


  findAllDocentes() {

    this._listaDocente = [];
    this._serviceCargo.findAllDocentes()
      .subscribe(res => {
        this._listaDocente = res;
        console.log(this._listaDocente);
      })
  }
  deleteDocente(id: number) {

    swal(`Una vez eliminado, no podrás recuperar este registro!`, {
      title: `Estas seguro?`,
      icon: `warning`,
      buttons: [`Cancel`, `Ok`],
      dangerMode: true
    })
      .then((willDelete) => {
        if (willDelete) {
          this._serviceCargo.deleteDocenteById(id)
            .subscribe(data => {

              if (data.mensaje == `The DELETE statement conflicted with the REFERENCE constraint "FK__TBL_REUNI__FK_IC__3E52440B". The conflict occurred in database "REUNION", table "dbo.TBL_REUNIONES", column 'FK_ICOD_DOCENTE'.`) {
                swal(`Lo siento, no de puede borrar`, `El registro esta relacionado con la Tabla Reuniones`, {
                  icon: `error`,
                });
              } else if (data.mensaje == `El registro ha sido eliminado con éxito.`) {
                swal(`Buen Trabajo`, ` ${data.mensaje}`, {
                  icon: `success`,
                });
              }
              this.findAllDocentes();
            });

        } else {
          swal(`Informacion`, `El registro no fue eliminado!`, `info`);
        }
      });
  }
}
