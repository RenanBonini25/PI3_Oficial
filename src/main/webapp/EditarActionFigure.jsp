<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Edição de Action Figure</title>
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
                    <a href="${pageContext.request.contextPath}/ListagemUsuarios"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="${pageContext.request.contextPath}/ListagemFiliais"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="Vendas_ProcurarCliente.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="${pageContext.request.contextPath}/GerarRelatorios.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <c:forEach items="${actFig}" var="action">
                <form action="${pageContext.request.contextPath}/EditarActionFigure" method="post" id="formularioCadastro">
                    <input type="hidden" value="${action.id}" name="id">
                    <input type="text" name="txtNome" value="${action.nome}" placeholder="NOME" class="formulario">
                    </br></br>
                    <input type="text" name="txtDescricao" value="${action.descricao}" placeholder="DESCRIÇÃO" class="formulario">
                    </br></br>
                    <input type="text" name="txtFabricante" value="${action.fabricante}" placeholder="FABRICANTE" class="formulario">
                    </br></br>
                    <input type="text" name="txtTamanho" value="${action.tamanho}" placeholder="TAMANHO" class="formSelect">
                    </br></br>
                    <input type="text" name="txtCor" value="${action.cor}" placeholder="COR" class="formSelect">
                    <input type="number" name="txtQuantidade" value="${action.quantidade}" placeholder="QUANTIDADE" class="formSelect">
                    </br></br>
                    <input type="text" name="txtPrecoCompra" value="${action.precoCompra}" placeholder="PREÇO COMPRA" class="formSelect">
                    <input type="text" name="txtPrecoVenda" value="${action.precoVenda}" placeholder="PREÇO VENDA" class="formSelect">
                    </br></br>
                    <input type="submit" value="SALVAR" id="botao">
                </form>
            </c:forEach>
        </div>
    </body>
</html>