<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Filial</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo, ${usuario.nome}</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="${pageContext.request.contextPath}/ListagemClientes"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="${pageContext.request.contextPath}/ListagemGames"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemConsoles"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemAcessorios"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemActionFigures"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="usuarios.jsp"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="filiais.jsp"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="Vendas_ProcurarCliente.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="${pageContext.request.contextPath}/GerarRelatorios.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <c:forEach items="${game}" var="game">
            <form action="${pageContext.request.contextPath}/EditarGame" method="post" id="formularioCadastro">
                <input type="hidden" value="${game.id}" name="id">
                <input type="text" value="${game.nome}" name="txtNome" placeholder="NOME" class="formulario">
                </br></br>
                <input type="text" value="${game.desenvolvedora}" name="txtDesenvolvedora" placeholder="DESENVOLVEDORA" class="formulario">
                </br></br>
                <select name="Plataforma" class="formSelect">
                    <option value="${game.plataforma}">${game.plataforma}</option>
                    <option value="PC">PC</option>
                    <option value="PS3">PS3</option>
                    <option value="PS4">PS4</option>
                    <option value="XBOX 360">XBOX 360</option>
                    <option value="XBOX ONE">XBOX ONE</option>
                </select>
                <input type="text" value="${game.quantidade}" name="txtQuantidade" placeholder="QUANTIDADE" class="formSelect">
                </br></br>
                <input type="text" value="${game.precoCompra}" name="txtPrecoCompra" placeholder="PREÇO COMPRA" class="formSelect">
                <input type="text" value="${game.precoVenda}" name="txtPrecoVenda" placeholder="PREÇO VENDA" class="formSelect">
                </br></br>
                <input type="text" value="${game.classIndicativa}" name="txtClassificacao" placeholder="CLASSIFICAÇÃO INDICATIVA" class="formSelect">
                <input type="text" value="${game.categoria}" name="txtCategoria" placeholder="CATEGORIA" class="formSelect">
                </br></br>
                <input type="submit" value="SALVAR" id="botao">
            </form>
            </c:forEach>
        </div>
    </body>
</html>
