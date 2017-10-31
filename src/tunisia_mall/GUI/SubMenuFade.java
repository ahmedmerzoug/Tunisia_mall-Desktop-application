/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.GUI;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Amine
 */
public class SubMenuFade {
    private static FadeTransition fade;

    private SubMenuFade() {
    }

    //criar animação fade
    public static void fade(Node no) {
        fade = new FadeTransition(Duration.seconds(1), no);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);
        fade.setAutoReverse(true);
        fade.play();
    }

    //criar animação fade definindo opacidade de origem, destino e duração da animação
    public static void fade(Node no, double inicio, double fim, int tempo) {
        fade = new FadeTransition(Duration.seconds(tempo), no);
        fade.setFromValue(inicio);
        fade.setToValue(fim);
        fade.setCycleCount(1);
        fade.setAutoReverse(true);
        fade.play();
    }

    //criar animação fade definindo opacidade de origem, destino e duração da animação
    public static void stopfade() {
        fade.stop();
    }
}
