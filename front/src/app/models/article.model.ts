import {Commentaire} from "./commentaire.model";

export interface Article {
  idArticle: number;
  themeId: number;
  titre: string;
  contenu: string;
  username: string;
  themeName: string;
  commentaires: Commentaire[],
  date?: Date;
}
