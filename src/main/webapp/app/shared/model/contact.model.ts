import { IPerson } from 'app/shared/model/person.model';

export interface IContact {
  id?: number;
  value?: string | null;
  person?: IPerson | null;
}

export const defaultValue: Readonly<IContact> = {};
