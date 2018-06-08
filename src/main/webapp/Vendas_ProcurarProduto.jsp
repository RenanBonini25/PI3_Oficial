<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Júpiter - Buscar Produto</title>
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
            <form action="${pageContext.request.contextPath}/ProcurarProduto" method="post" id="formularioCadastro">
                <c:out value="Cliente: ${sessionScope.cliente.nome}"></c:out>
                <p>Buscar Produto</p>
                <input type="text" name="txtProduto" placeholder="PRODUTO" class="formulario">
                </br></br>

                <input type="submit" value="Buscar" id="botao">
            </form>

        </div>
    </body>
</html>


