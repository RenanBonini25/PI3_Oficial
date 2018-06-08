<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Games</title>
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
            <a href="${pageContext.request.contextPath}/CadastrarGame.jsp"><img class="add" src="_imagens/add.png"></a>
            <table class="tabelaResultados">
                <tr>
                    <td><strong>Nome</strong></td>
                    <td><strong>Plataforma</strong></td>
                    <td><strong>Desenvolvedora</strong></td>
                    <td><strong>Quantidade</strong></td>
                    <td><strong>Preço Compra</strong></td>
                    <td><strong>Preço Venda</strong></td>
                </tr>
                <c:forEach items="${Listagem}" var="game">
                    <tr>
                        <td>${game.nome}</td>
                        <td>${game.plataforma}</td>
                        <td>${game.desenvolvedora}</td>
                        <td>${game.quantidade}</td>
                        <td>${game.precoCompra}</td>
                        <td>${game.precoVenda}</td>

                        <td>
                            <a href="EditarGame?id=${game.id}">Editar</a> 
                            <a href="ExcluirGame?id=${game.id}">Deletar</a> 
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>
