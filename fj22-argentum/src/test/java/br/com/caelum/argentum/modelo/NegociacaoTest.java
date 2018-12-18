package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		// se criar um negocio no dia 15...
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);

		// ainda que eu tente mudar a data para 20...
		n.getData().set(Calendar.DAY_OF_MONTH, 20);

		// ele continua no dia 15.
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar janeiro = new GregorianCalendar(2011, 1, 20, 8, 30);
		Calendar fevereiro = new GregorianCalendar(2011, 2, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, janeiro);
		Assert.assertFalse(negociacao.isMesmoDia(fevereiro));
	}

	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar ano2010 = new GregorianCalendar(2010, 10, 20, 8, 30);
		Calendar ano2011 = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, ano2010);
		Assert.assertFalse(negociacao.isMesmoDia(ano2011));

	}

}
