package test;

import java.util.ArrayList;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import model.NCM;

public class HtmlUnit {

	private ArrayList<HtmlElement> listElements = new ArrayList<HtmlElement>();

	private ArrayList<NCM> listNCM = new ArrayList<NCM>();

	private final WebClient webClient = new WebClient();

	
	public void paginaIncial() {

		try {

			HtmlPage page = webClient.getPage("http://www4.receita.fazenda.gov.br/simulador/PesquisarNCM.jsp");

			HtmlElement cboCodCapitulo = (HtmlElement) page.getElementById("cboCapitulo");

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
			listElements.remove(0);
			
			/*
			 * Obtem todos os valores de cada Elemento, Código do Capitulo e
			 * Capitulo, e atribui cada um eles a uma instância de NCM
			 */
			for (HtmlElement elementoHtml : listElements) {

				String elemento = elementoHtml.asText();
				String[] vElements = elemento.split(" - ");

				NCM novo = new NCM();
				novo.setCodigoCapitulo(vElements[0]);
				novo.setCapitulo(vElements[1]);

				/*
				 * Aparentemente o código NCM é uma string, na apresentação
				 * código de capitulo com apenas 1 digito possuem zero na frente
				 * (02)
				 */

				listNCM.add(novo);
			}

			for (NCM ncm : listNCM) {

				/*
				 * Redireciona para a página de cada Capitulo extraindo todos os
				 * NCMs, que possuem os dois primeiros dígitos iguais ao
				 * codigoCapitulo *
				 */
				page = webClient.getPage("http://www4.receita.fazenda.gov.br/simulador/PesquisarNCM.jsp?" + "codigo="
						+ ncm.getCodigoCapitulo() + "&" + "codigoCapitulo=" + ncm.getCodigoCapitulo() + "&"
						+ "codigoPosicao=" + "&button=Exibir+NCMs");

				/*
				 * Elemento div com todos os NCMs
				 */
				HtmlElement divListaNCMs = (HtmlElement) page.getElementById("listaNCM");

				/*
				 * Obtem todos os valores dos NCMs na <div> listaNCM, e atribui
				 * cada um eles a uma instância de NCM
				 */
				int nSubCap = 0;
				for (DomElement dom : divListaNCMs.getChildElements()) {

					HtmlElement elemento = (HtmlElement) dom;
					

					/*
					 * Caso o elemento seja <br/> continua a procurar HTML com
					 * NCMs. No windows <br/> é equivalente a \r\n, no Linux \n.
					 * É necessário replace() por que <br/> cria uma quebra de
					 * linha no terminal.
					 */
					if (!elemento.asXml().replace("\r\n", "").equalsIgnoreCase("<br/>")) {

						String sElemento = elemento.asText();
						String[] vElements = sElemento.split(" - ");

						ncm.getCodigo().add(vElements[0]);
						ncm.getDescricao().add(vElements[1]);

						System.out.println("Código NCM: " + ncm.getCodigo().get(nSubCap) + " - Descrição: "
								+ ncm.getDescricao().get(nSubCap) + "\n" + ncm.getCodigo().get(nSubCap) + " - "
								+ ncm.getDescricao().get(nSubCap)+"\n");
						nSubCap++;
					}					
				}

			}
		} catch (Exception e) {

			System.out.println("Test failed. Exception e:" + e.getMessage());
		}
	}
}