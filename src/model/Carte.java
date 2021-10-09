package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Carte {
    private ImageView image;
    private ImageView verso;
    private String classe;

    public Carte(String classe) {
        /////////////////////////////////////////////////
        //Mise des images colorées à ses cartes respectives
        /////////////////////////////////////////////////
        if("Bleu".equals(classe)){
            File fileCarteBleu = new File("src\\vue\\ressources\\images\\bleu.png");
            String pathCarteBleu = fileCarteBleu.toURI().toString();
            final Image imgCarteBleu = new Image(pathCarteBleu,false);
            ImageView imageverso = new ImageView(imgCarteBleu);
            this.verso=imageverso;
        }
        if("Rouge".equals(classe)){
            File fileCarteRouge = new File("src\\vue\\ressources\\images\\rouge.png");
            String pathCarteRouge = fileCarteRouge.toURI().toString();
            final Image imgCarteRouge = new Image(pathCarteRouge,false);
            ImageView imageverso = new ImageView(imgCarteRouge);
            this.verso=imageverso;
        }
        if("Vert".equals(classe)){
            File fileCarteVert = new File("src\\vue\\ressources\\images\\vert.png");
            String pathCarteVert = fileCarteVert.toURI().toString();
            final Image imgCarteVert = new Image(pathCarteVert,false);
            ImageView imageverso = new ImageView(imgCarteVert);
            this.verso=imageverso;
        }
        if("Jaune".equals(classe)){
            File fileCarteJaune = new File("src\\vue\\ressources\\images\\jaune.png");
            String pathCarteJaune = fileCarteJaune.toURI().toString();
            final Image imgCarteJaune = new Image(pathCarteJaune,false);
            ImageView imageverso = new ImageView(imgCarteJaune);
            this.verso=imageverso;
        }
        if("Violet".equals(classe)){
            File fileCarteViolet = new File("src\\vue\\ressources\\images\\violet.png");
            String pathCarteViolet = fileCarteViolet.toURI().toString();
            final Image imgCarteViolet = new Image(pathCarteViolet,false);
            ImageView imageverso = new ImageView(imgCarteViolet);
            this.verso=imageverso;
        }
        if("Noir".equals(classe)){
            File fileCarteNoir = new File("src\\vue\\ressources\\images\\noir.png");
            String pathCarteNoir = fileCarteNoir.toURI().toString();
            final Image imgCarteNoir = new Image(pathCarteNoir,false);
            ImageView imageverso = new ImageView(imgCarteNoir);
            this.verso=imageverso;
        }
        this.classe=classe;
    }



    public ImageView getImage() {
        return image;
    }

    public ImageView getVerso() {
        return verso;
    }

    public String getClasse() {
        return classe;
    }

    public void setImage(String string) {
        /////////////////////////////////////////////////
        //Mise en place de nos thème ( à rajouter ici pour plus de thème)
        /////////////////////////////////////////////////
        if("Pokemon".equals(string)){
            File fileRecto = new File("src\\vue\\ressources\\images\\cartepokemon.png");
            String pathCarteRecto = fileRecto.toURI().toString();
            Image imgRecto = new Image(pathCarteRecto,false);
            ImageView imageRecto = new ImageView(imgRecto);
            this.image = imageRecto;
        }
            if("HearthStone".equals(string)){
            File fileRecto = new File("src\\vue\\ressources\\images\\carte.png");
            String pathCarteRecto = fileRecto.toURI().toString();
            Image imgRecto = new Image(pathCarteRecto,false);
            ImageView imageRecto = new ImageView(imgRecto);
            this.image = imageRecto;
        }
    }
}
