import { Course } from './../../model/course';
import { CoursesService } from '../../service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from '../../components/confirmation-dialog/confirmation-dialog.component';

@Component({
	selector: 'app-courses',
	templateUrl: './courses.component.html',
	styleUrls: ['./courses.component.css'],
})
export class CoursesComponent implements OnInit {
	courses$: Observable<Course[]> | null = null;
	readonly displayedColumns = ['name', 'category', 'hours', 'actions'];

	//courseService :CoursesService;

	constructor(
		private courseService: CoursesService,
		public dialog: MatDialog,
		private router: Router,
		private route: ActivatedRoute,
		private snackBar: MatSnackBar
	) {
		//this.courses = [];
		//this.courseService = new CoursesService();
		this.refresh();
	}
	ngOnInit(): void {}

	refresh() {
		this.courses$ = this.courseService.list().pipe(
			catchError((error) => {
				this.onError('Erro ao carregar cursos');
				return of([]);
			})
		);
	}

	onError(errorMsg: string) {
		this.dialog.open(ErrorDialogComponent, {
			data: errorMsg,
		});
	}

	onAdd() {
		this.router.navigate(['new'], { relativeTo: this.route });
	}

	onEdit(course: Course) {
		this.router.navigate(['edit', course._id], { relativeTo: this.route });
	}

	onDelete(course: Course) {
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: 'Tem certeza que deseja remover este curso ?',
		});

		dialogRef.afterClosed().subscribe((result: boolean) => {
			if (result) {
				this.courseService.delete(course._id).subscribe(
					() => {
						this.refresh();
						this.snackBar.open('Curso removido com sucesso', 'X', {
							duration: 5000,
							verticalPosition: 'top',
							horizontalPosition: 'center',
						});
					},
					() => this.onError('Erro ao tentar remover curso')
				);
			}
		});
	}
}
