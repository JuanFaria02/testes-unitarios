package br.ce.juanfaria.servicos;

import br.ce.juanfaria.entidades.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//Classe para estudar assetions

public class AssertTest {
    @Test
    public void testTrue(){
        //desse jeito se for falso o teste não passa
        Assert.assertTrue(true);
        //O teste só passa se a condição for falsa
        Assert.assertFalse(false);
        //verifica se um valor é igual a outro, tratando cada tipo de uma forma diferente
        Assert.assertEquals(1,1);
        Assert.assertEquals(Math.PI, 3.14, 0.01);//Diz que queremos comparar apenas até a segunda casa decimal

        Usuario usuario = new Usuario("Jose");
        Usuario usuario1 = new Usuario("Jose");
        Usuario usuario2 = null;
        //Verifica as instâncias, para compara-los precisamos do equals e hashcode
        Assert.assertEquals(usuario, usuario1);

        //Para niveis de instancia
        Assert.assertSame(usuario1, usuario1);

        //Verificar se é null, duas possibilidades
        Assert.assertTrue(usuario2 == null);
        Assert.assertNull(usuario2);

        //Outras assertions
        Assert.assertNotEquals(usuario1, usuario2);

        Assert.assertNotNull(usuario1);

    }

}
