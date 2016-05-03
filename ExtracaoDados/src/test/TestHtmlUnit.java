package test;

import java.util.ArrayList;

import org.apache.bcel.generic.DCONST;
import org.junit.Assert;
import org.junit.Test;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import model.NCM;

public class TestHtmlUnit {

	private ArrayList<HtmlElement> listElements = new ArrayList<HtmlElement>();

	private ArrayList<NCM> listNCM = new ArrayList<NCM>();

	@Test
	public void PaginaIncial() {

		try {

			final WebClient webClient = new WebClient();

			final HtmlPage page = webClient.getPage("http://www4.receita.fazenda.gov.br/simulador/PesquisarNCM.jsp");

			final HtmlElement cboCodCapitulo = (HtmlElement) page.getElementById("cboCapitulo");

			/*
			 * Captura todos os elementos do combo box CodCapitulo na páginal de
			 * seleção do Capitulo.
			 */
			for (DomElement dom : cboCodCapitulo.getChildElements()) {

				HtmlElement elemento = (HtmlElement) dom;

				listElements.add(elemento);
			}

			/*
			 * Remove o primeiro item da lista de Elemenetos Html 1º Elemento :
			 * * Selecione *
			 */
			System.out.println(listElements.remove(0));

			System.out.println(listElements.get(0).asXml());

			/*
			 * Obtem todos os valores de cada Elemento, Código do Capitulo e
			 * Capitulo, e atribui cada um eles a uma instância de NCM
			 */
			for (HtmlElement elementoHtml : listElements) {

				String elemento = elementoHtml.asText();
				String[] vElements = elemento.split(" - ");
				System.out.println(vElements.length);

				NCM novo = new NCM();
				novo.setCodigoCapitulo(vElements[0]);
				novo.setCapitulo(vElements[1]);

				/*
				 * Aparentemente o código NCM é uma string, na apresentação
				 * código de capitulo com apenas 1 digito possuem zero na frente
				 * (02)
				 */
				System.out.println("Código Cap: " + novo.getCodigoCapitulo() + "\nCapitulo: " + novo.getCapitulo());

				listNCM.add(novo);
			}

			// final HtmlPage pageCap = webClient.getPage(
			// "http://www4.receita.fazenda.gov.br/simulador/PesquisarNCM.jsp?codigo=01&codigoCapitulo=01");

			// final HtmlElement eleCodCapituloPageCap = (HtmlElement)
			// pageCap.getElementById("cboCapitulo");
			// final HtmlElement eleCodPosicaoPageCap = (HtmlElement)
			// pageCap.getElementById("cboPosicao");
			// System.out.println("Escolha: " + eleCodPosicaoPageCap.asText());

			webClient.close();
		} catch (Exception e) {

			System.out.println("Test failed. Exception e:" + e.getMessage());
		}
	}

	@Test
	public void PaginaPosicao() {

		try {

			final WebClient webClient = new WebClient();

			for (NCM ncm : listNCM) {

				final HtmlPage page = webClient.getPage("http://www4.receita.fazenda.gov.br/simulador/PesquisarNCM.jsp?"
						+ "codigo=" + ncm.getCodigo() + "&" + "codigoCapitulo=" + ncm.getCapitulo());

				final HtmlElement cboCodPosicao = (HtmlElement) page.getElementById("cboPosicao");
				System.out.println("Escolha: " + cboCodPosicao.asText());

				listElements = new ArrayList<HtmlElement>();

				/*
				 * Captura todos os elementos do combo box CodPosicao na página
				 * de seleção de Posição.
				 */
				for (DomElement dom : cboCodPosicao.getChildElements()) {

					HtmlElement elemento = (HtmlElement) dom;
					listElements.add(elemento);
				}

				/*
				 * Remove o primeiro item da lista de Elemenetos Html. 1º
				 * Elemento : * Selecione *
				 */
				System.out.println(listElements.remove(0));

				System.out.println(listElements.get(0).asXml());

				/*
				 * Obtem todos os valores de cada Elemento, Código da Posição e
				 * Posição, e atribui cada um eles a uma instância de NCM na
				 * listNCM
				 */

				int nPOSs = 0;
				for (HtmlElement elementoHtml : listElements) {

					String elemento = elementoHtml.asText();
					String[] vElements = elemento.split(" - ");
					System.out.println(vElements.length);

					ArrayList<String> listCodigoPosicao = ncm.getCodigoPosicao();
					ArrayList<String> listPosicao = ncm.getPosicao();

					// TESTAR ASSIM QUEM O LINK DA RECEITA
					// DO SIMULADOR VOLTAR A FUNCIONAR
					listCodigoPosicao.add(vElements[0]);
					listPosicao.add(vElements[1]);

					System.out.println(
							"Código Pos: " + ncm.getCodigoPosicao().get(nPOSs)
							+ "\nPosicao: " +  ncm.getPosicao().get(nPOSs));

					nPOSs++;
				}

				webClient.close();
			}
		} catch (Exception e) {

			System.out.println("Test failed. Exception e:" + e.getMessage());
		}
	}

}